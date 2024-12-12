package com.github.maximovj.linuxhubapi.linuxhub_api.data.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum State {
    active,
    reactive,
    recover,
    block,
    delete;
}
