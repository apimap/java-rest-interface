/*
Copyright 2021-2023 TELENOR NORGE AS

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package io.apimap.api.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.apimap.rest.RootRestEntity;
import io.apimap.rest.jsonapi.JsonApiRestResponseWrapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetadataRootRestEntity extends RootRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.METADATA_CONTAINER;

    public static final String VERSION = "1.0.0";

    protected MetadataDataRestEntity data;

    protected String version;

    public MetadataRootRestEntity() {
    }

    public MetadataRootRestEntity(MetadataDataRestEntity data) {
        this.data = data != null ? (MetadataDataRestEntity) data.clone() : null;
        this.version = VERSION;
    }

    public MetadataRootRestEntity(MetadataDataRestEntity data, String version) {
        this.data = data != null ? (MetadataDataRestEntity) data.clone() : null;
        this.version = version;
    }

    public MetadataDataRestEntity getData() {
        return data != null ? (MetadataDataRestEntity) data.clone() : null;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "MetadataRootRestEntity{" +
                "data=" + data +
                ", version='" + version + '\'' +
                '}';
    }
}
