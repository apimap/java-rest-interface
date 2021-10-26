/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
 */

package io.apimap.api.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetadataRootRestEntity extends RootRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.METADATA_CONTAINER;

    public static final String VERSION = "1.0.0";

    protected MetadataDataRestEntity data;

    protected String version;

    public MetadataRootRestEntity() {
    }

    public MetadataRootRestEntity(MetadataDataRestEntity data) {
        this.data = data;
        this.version = VERSION;
    }

    public MetadataRootRestEntity(MetadataDataRestEntity data, String version) {
        this.data = data;
        this.version = version;
    }

    public MetadataDataRestEntity getData() {
        return data;
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
