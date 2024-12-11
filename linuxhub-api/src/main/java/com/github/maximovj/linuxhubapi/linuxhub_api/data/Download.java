package com.github.maximovj.linuxhubapi.linuxhub_api.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Download {
    String link;
    String format;
    String size;
}
