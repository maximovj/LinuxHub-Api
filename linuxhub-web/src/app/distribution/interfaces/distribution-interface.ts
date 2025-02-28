// Generated by https://quicktype.io

export interface Distribution {
  id?:                 string | null;
  name?:               string | null;
  description?:        string | null;
  enterprise_support?: string | null;
  official_site?:      string | null;
  logo?:               string | null;
  processor?:          string | null;
  license?:            string | null;
  feature?:            Feature | null;
  technician?:         Technician | null;
  tags?:               string[] | null;
  downloads?:          Download[] | null;
  created_at?:         Date | null;
  updated_at?:         Date | null;
}

export interface Download {
  link?:   string | null;
  format?: string | null;
  size?:   string | null;
}

export interface Feature {
  focus?:                 string | null;
  package_manager?:       string | null;
  default_environment?:   string | null;
  gpu_support?:           string | null;
  uefi_compatibility?:    boolean | null;
  bios_compatibility?:    boolean | null;
  certifications?:        boolean | null;
  memory_efficiency?:     string | null;
  energy_consumption?:    null | null;
  community_size?:        string | null;
  documentation_quality?: string | null;
}

export interface Technician {
  distrowatch_ranking?:  number | null;
  steam_popularity?:     number | null;
  ram?:                  number | null;
  disk?:                 number | null;
  installation_time?:    number | null;
  configuration_time?:   number | null;
  boot_speed?:           number | null;
  speed?:                number | null;
  ram_consumption_idle?: number | null;
  cpu_consumption_idle?: number | null;
}
