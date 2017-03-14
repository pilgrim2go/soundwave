package com.pinterest.cmp.cmdb.pinterest;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * The white list allowed to appear in facts.
 * Facts can contain arbitrary fields that result ES index mapping grows to be too big.
 * Create a white list based on all running instances and filter out the per instance
 * field.
 * Ideally, we can make it a managed list to config. Let's leave it as code right now until
 * we think we need to change it
 */
public class EsFactsWhitelist {

  private static final String[] elements = new String[]{
      "architecture",
      "augeasversion",
      "bios_release_date",
      "bios_vendor",
      "bios_version",
      "blockdevice_sda_model",
      "blockdevice_sda_size",
      "blockdevice_sda_vendor",
      "blockdevices",
      "cmp_group",
      "concat_basedir",
      "deploy_service",
      "deploy_service_combined",
      "deployd_services",
      "deployment",
      "domain",
      "ec2_ami_id",
      "ec2_ami_launch_index",
      "ec2_ami_manifest_path",
      "ec2_ancestor_ami_ids",
      "ec2_ancestor_ami_ids_0",
      "ec2_ancestor_ami_ids_1",
      "ec2_hostname",
      "ec2_iam_info_0",
      "ec2_iam_info_1",
      "ec2_iam_info_2",
      "ec2_iam_info_3",
      "ec2_iam_info_4",
      "ec2_iam_info_5",
      "ec2_instance_action",
      "ec2_instance_id",
      "ec2_instance_type",
      "ec2_kernel_id",
      "ec2_local_hostname",
      "ec2_local_ipv4",
      "ec2_mac",
      "ec2_metadata",
      "ec2_metrics_vhostmd",
      "ec2_placement_availability_zone",
      "ec2_profile",
      "ec2_public_hostname",
      "ec2_public_ipv4",
      "ec2_public_keys_0_openssh_key",
      "ec2_reservation_id",
      "ec2_security_groups",
      "ec2_security_groups_0",
      "ec2_security_groups_1",
      "ec2_services_domain",
      "ec2_services_partition",
      "ec2_userdata",
      "ec2_vpc_id",
      "facterversion",
      "filesystems",
      "fqdn",
      "gid",
      "hadoop_volumes",
      "hardwareisa",
      "hardwaremodel",
      "hostname",
      "host_type",
      "id",
      "interfaces",
      "ipaddress",
      "ipaddress_bridge0",
      "ipaddress_docker0",
      "ipaddress_eth0",
      "ipaddress_eth1",
      "ipaddress_lo",
      "ipaddress_lxcbr0",
      "is_virtual",
      "kernel",
      "kernelmajversion",
      "kernelrelease",
      "kernelversion",
      "lsbdistcodename",
      "lsbdistdescription",
      "lsbdistid",
      "lsbdistrelease",
      "lsbmajdistrelease",
      "lsbrelease",
      "manufacturer",
      "memoryfree",
      "memoryfree_mb",
      "memoryfreeinbytes",
      "memorysize",
      "memorysize_mb",
      "memorysizeinbytes",
      "memorytotal",
      "metrics_agent_carbon_to_tsdb",
      "namespace",
      "netmask",
      "netmask_bridge0",
      "netmask_docker0",
      "netmask_eth0",
      "netmask_eth1",
      "netmask_lo",
      "netmask_lxcbr0",
      "network_bridge0",
      "network_docker0",
      "network_eth0",
      "network_eth1",
      "network_lo",
      "network_lxcbr0",
      "newpuppet",
      "node_group",
      "obelix_cluster_name",
      "obelix_corpus_type",
      "obelix_index_type",
      "operatingsystem",
      "operatingsystemmajrelease",
      "operatingsystemrelease",
      "os",
      "osfamily",
      "partitions",
      "path",
      "physicalprocessorcount",
      "pinfo_cloud",
      "pinfo_cloud_region",
      "pinfo_cluster",
      "pinfo_environment",
      "pinfo_role",
      "pinfo_team",
      "pinterest_is_adminapp",
      "processor0",
      "processor1",
      "processor10",
      "processor11",
      "processor12",
      "processor13",
      "processor14",
      "processor15",
      "processor16",
      "processor17",
      "processor18",
      "processor19",
      "processor2",
      "processor20",
      "processor21",
      "processor22",
      "processor23",
      "processor24",
      "processor25",
      "processor26",
      "processor27",
      "processor28",
      "processor29",
      "processor3",
      "processor30",
      "processor31",
      "processor4",
      "processor5",
      "processor6",
      "processor7",
      "processor8",
      "processor9",
      "processorcount",
      "processors",
      "productname",
      "puppet_canary",
      "puppet_classes",
      "puppet_groups",
      "puppetversion",
      "role",
      "rubyplatform",
      "rubysitedir",
      "rubyversion",
      "selinux",
      "serialnumber",
      "softlayer_instance_id",
      "supervisord_has_aws_env",
      "swapfree",
      "swapfree_mb",
      "swapfreeinbytes",
      "swapsize",
      "swapsize_mb",
      "swapsizeinbytes",
      "system_uptime",
      "timezone",
      "type",
      "uniqueid",
      "updateTime",
      "uptime",
      "uptime_days",
      "uptime_hours",
      "uptime_seconds",
      "uuid",
      "virtual",
      "visibility_cluster_name",
      "visibility_cluster_type",
      "volumes",
      };

  private static HashSet<String> whiteListSet = new HashSet<String>(Arrays.asList(elements));

  public static boolean inWhiteList(String value) {
    if (StringUtils.isEmpty(value)) {
      return false;
    }

    return whiteListSet.contains(value);
  }
}
