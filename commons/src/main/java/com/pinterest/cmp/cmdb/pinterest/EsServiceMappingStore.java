package com.pinterest.cmp.cmdb.pinterest;

import com.pinterest.cmp.cmdb.elasticsearch.EsStore;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.time.StopWatch;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The elastic search index storing the service mapping info
 */
public class EsServiceMappingStore extends EsStore implements ServiceMappingStore {

  private static final Logger logger = LoggerFactory.getLogger(EsServiceMappingStore.class);
  private static final ObjectMapper mapper = new ObjectMapper()
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  public EsServiceMappingStore() {
    super();
  }

  public EsServiceMappingStore(String host, int port) {
    super(host, port);
  }

  @Override
  public String getIndexName() {
    return "misc";
  }

  @Override
  public String getDocTypeName() {
    return "servicemapping";
  }

  public List<EsServiceMapping> getServiceMappings() throws Exception {
    StopWatch sw = new StopWatch();
    sw.start();
    List<EsServiceMapping> ret = new ArrayList<>();
    SearchResponse result = getByDocType(10000);
    for (SearchHit hit : result.getHits()) {
      try {
        EsServiceMapping
            serviceMapping =
            mapper.readValue(hit.getSourceAsString(), EsServiceMapping.class);
        serviceMapping.buildMatchPatterns();
        ret.add(serviceMapping);
      } catch (Exception ex) {
        logger.error("Cannot create Service mapping from {}", hit.getSourceAsString());
      }
    }
    sw.stop();
    logger.info("Refresh all service mappings in {} ms", sw.getTime());
    return ret;
  }

  @Override
  public long updateOrInsertServiceMapping(EsServiceMapping serviceMapping) throws Exception {

    UpdateResponse response = this.updateOrInsert(serviceMapping.getName(),
        mapper.writeValueAsBytes(serviceMapping),
        mapper.writeValueAsBytes(serviceMapping));

    return response.isCreated() ? 0 : response.getVersion();
  }

  @Override
  public EsServiceMapping getServiceMappingByName(String name) throws Exception {

    SearchResponse response = this.retrieveByField("name", name, EsServiceMapping.class);
    if (response.getHits().totalHits() > 0) {

      String str = response.getHits().getAt(0).getSourceAsString();
      EsServiceMapping serviceMapping = mapper.readValue(str, EsServiceMapping.class);
      return serviceMapping;
    }
    return null;
  }
}
