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

import com.fasterxml.jackson.annotation.*;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.apimap.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Metadata",
        description = "Object describing the metadata collection for each API version"
)
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class MetadataDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.METADATA_ELEMENT;
    public static final String NAME_KEY = "name";
    public static final String VISIBILITY_KEY = "visibility";
    public static final String API_VERSION_KEY = "api version";
    public static final String RELEASE_STATUS_KEY = "release status";
    public static final String INTERFACE_SPECIFICATION_KEY = "interface specification";
    public static final String INTERFACE_DESCRIPTION_LANGUAGE_KEY = "interface description language";
    public static final String ARCHITECTURE_LAYER_KEY = "architecture layer";
    public static final String BUSINESS_UNIT_KEY = "business unit";
    public static final String SYSTEM_IDENTIFIER_KEY = "system identifier";
    public static final String DOCUMENTATION_KEY = "documentation";
    public static final String DESCRIPTION_KEY = "description";

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @JsonIgnore
    protected String uri;

    @JsonIgnore
    protected HashMap<String, Object> links = new HashMap<>();

    /*
    Name
     */
    @Schema(hidden = true)
    @JsonIgnore
    protected String name;

    @Schema(hidden = true)
    @JsonIgnore
    protected String visibility;

    @Schema(hidden = true)
    @JsonIgnore
    protected String description;

    /*
    Version
     */
    @Schema(hidden = true)
    @JsonIgnore
    protected String apiVersion;

    @Schema(hidden = true)
    @JsonIgnore
    protected String releaseStatus;

    @Schema(hidden = true)
    @JsonIgnore
    protected String systemIdentifier;

    @Schema(hidden = true)
    @JsonIgnore
    protected List<String> documentation;

    /*
    Implementation
     */
    @Schema(hidden = true)
    @JsonIgnore
    protected String interfaceSpecification;

    @Schema(hidden = true)
    @JsonIgnore
    protected String interfaceDescriptionLanguage;

    /*
    Organization
     */
    @Schema(hidden = true)
    @JsonIgnore
    protected String architectureLayer;

    @Schema(hidden = true)
    @JsonIgnore
    protected String businessUnit;

    public MetadataDataRestEntity() {
    }

    public MetadataDataRestEntity(final String name,
                                  final String description,
                                  final String visibility,
                                  final String apiVersion,
                                  final String releaseStatus,
                                  final String interfaceSpecification,
                                  final String interfaceDescriptionLanguage,
                                  final String architectureLayer,
                                  final String businessUnit,
                                  final String systemIdentifier,
                                  final List<String> documentation) {
        super(name + "#" + apiVersion);

        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.apiVersion = apiVersion;
        this.releaseStatus = releaseStatus;
        this.interfaceSpecification = interfaceSpecification;
        this.interfaceDescriptionLanguage = interfaceDescriptionLanguage;
        this.architectureLayer = architectureLayer;
        this.businessUnit = businessUnit;
        this.systemIdentifier = systemIdentifier;
        this.documentation = new ArrayList<>(documentation);
    }

    public MetadataDataRestEntity(final String name,
                                  final String description,
                                  final String visibility,
                                  final String apiVersion,
                                  final String releaseStatus,
                                  final String interfaceSpecification,
                                  final String interfaceDescriptionLanguage,
                                  final String architectureLayer,
                                  final String businessUnit,
                                  final String systemIdentifier,
                                  final List<String> documentation,
                                  final String uri) {
        super(name + "#" + apiVersion);

        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.apiVersion = apiVersion;
        this.releaseStatus = releaseStatus;
        this.interfaceSpecification = interfaceSpecification;
        this.interfaceDescriptionLanguage = interfaceDescriptionLanguage;
        this.architectureLayer = architectureLayer;
        this.businessUnit = businessUnit;
        this.uri = uri;
        this.systemIdentifier = systemIdentifier;
        this.documentation = new ArrayList<>(documentation);
    }

    @Override
    public Object clone() {
        MetadataDataRestEntity returnValue = (MetadataDataRestEntity) super.clone();

        returnValue.name = this.name;
        returnValue.description = this.description;
        returnValue.visibility = this.visibility;
        returnValue.apiVersion = this.apiVersion;
        returnValue.releaseStatus = this.releaseStatus;
        returnValue.interfaceSpecification = this.interfaceSpecification;
        returnValue.interfaceDescriptionLanguage = this.interfaceDescriptionLanguage;
        returnValue.architectureLayer = this.architectureLayer;
        returnValue.businessUnit = this.businessUnit;
        returnValue.systemIdentifier = this.systemIdentifier;
        returnValue.documentation = new ArrayList<>(this.documentation);
        returnValue.uri = this.uri;

        return returnValue;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getReleaseStatus() {
        return releaseStatus;
    }

    public String getInterfaceSpecification() {
        return interfaceSpecification;
    }

    public String getInterfaceDescriptionLanguage() {
        return interfaceDescriptionLanguage;
    }

    public String getArchitectureLayer() {
        return architectureLayer;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public List<String> getDocumentation() {
        return new ArrayList<>(documentation);
    }

    public String getSystemIdentifier() {
        return systemIdentifier;
    }

    @Schema(
            name="Metadata Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes implements Cloneable {
        @JsonProperty(NAME_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String name;

        @JsonProperty(VISIBILITY_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String visibility;

        @JsonProperty(DESCRIPTION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String description;

        @JsonProperty(API_VERSION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String apiVersion;

        @JsonProperty(RELEASE_STATUS_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String releaseStatus;

        @JsonProperty(SYSTEM_IDENTIFIER_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String systemIdentifier;

        @JsonProperty(DOCUMENTATION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected List<String> documentation;

        @JsonProperty(INTERFACE_SPECIFICATION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String interfaceSpecification;

        @JsonProperty(INTERFACE_DESCRIPTION_LANGUAGE_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String interfaceDescriptionLanguage;

        @JsonProperty(ARCHITECTURE_LAYER_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String architectureLayer;

        @JsonProperty(BUSINESS_UNIT_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String businessUnit;

        public Attributes() {
        }

        public Attributes(final String name,
                          final String visibility,
                          final String description,
                          final String apiVersion,
                          final String releaseStatus,
                          final String systemIdentifier,
                          final List<String> documentation,
                          final String interfaceSpecification,
                          final String interfaceDescriptionLanguage,
                          final String architectureLayer,
                          final String businessUnit) {
            this.name = name;
            this.visibility = visibility;
            this.description = description;
            this.apiVersion = apiVersion;
            this.releaseStatus = releaseStatus;
            this.systemIdentifier = systemIdentifier;
            this.documentation = new ArrayList<>(documentation);
            this.interfaceSpecification = interfaceSpecification;
            this.interfaceDescriptionLanguage = interfaceDescriptionLanguage;
            this.architectureLayer = architectureLayer;
            this.businessUnit = businessUnit;
        }

        @Override
        public Object clone() {
            Attributes returnValue = null;

            try {
                returnValue = (Attributes) super.clone();
            } catch (CloneNotSupportedException e) {
                returnValue = new Attributes();
            }

            returnValue.name = this.name;
            returnValue.visibility = this.visibility;
            returnValue.description = this.description;
            returnValue.apiVersion = this.apiVersion;
            returnValue.releaseStatus = this.releaseStatus;
            returnValue.systemIdentifier = this.systemIdentifier;
            returnValue.documentation = new ArrayList<>(this.documentation);
            returnValue.interfaceSpecification = this.interfaceSpecification;
            returnValue.interfaceDescriptionLanguage = this.interfaceDescriptionLanguage;
            returnValue.architectureLayer = this.architectureLayer;
            returnValue.businessUnit = this.businessUnit;

            return returnValue;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "name='" + name + '\'' +
                    ", visibility='" + visibility + '\'' +
                    ", description='" + description + '\'' +
                    ", apiVersion='" + apiVersion + '\'' +
                    ", releaseStatus='" + releaseStatus + '\'' +
                    ", systemIdentifier='" + systemIdentifier + '\'' +
                    ", documentation=" + documentation +
                    ", interfaceSpecification='" + interfaceSpecification + '\'' +
                    ", interfaceDescriptionLanguage='" + interfaceDescriptionLanguage + '\'' +
                    ", architectureLayer='" + architectureLayer + '\'' +
                    ", businessUnit='" + businessUnit + '\'' +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    public Attributes getAttributes() {
        return new Attributes(
                name,
                visibility,
                description,
                apiVersion,
                releaseStatus,
                systemIdentifier,
                documentation,
                interfaceSpecification,
                interfaceDescriptionLanguage,
                architectureLayer,
                businessUnit
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.name = (String) attributes.getOrDefault(NAME_KEY, null);
        this.description = (String) attributes.getOrDefault(DESCRIPTION_KEY, null);
        this.visibility = (String) attributes.getOrDefault(VISIBILITY_KEY, null);
        this.apiVersion = (String) attributes.getOrDefault(API_VERSION_KEY, null);
        this.releaseStatus = (String) attributes.getOrDefault(RELEASE_STATUS_KEY, null);
        this.interfaceSpecification = (String) attributes.getOrDefault(INTERFACE_SPECIFICATION_KEY, null);
        this.interfaceDescriptionLanguage = (String) attributes.getOrDefault(INTERFACE_DESCRIPTION_LANGUAGE_KEY, null);
        this.architectureLayer = (String) attributes.getOrDefault(ARCHITECTURE_LAYER_KEY, null);
        this.businessUnit = (String) attributes.getOrDefault(BUSINESS_UNIT_KEY, null);
        this.systemIdentifier = (String) attributes.getOrDefault(SYSTEM_IDENTIFIER_KEY, null);
        this.documentation = (List<String>) attributes.getOrDefault(DOCUMENTATION_KEY, Arrays.asList());
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, Object> getLinks() {
        HashMap<String,Object> links = new HashMap<String,Object>();

        if(this.uri != null) links.put("self", uri);
        if(this.links != null) links.putAll(this.links);

        if(links.size() < 1) return null;
        return links;
    }

    public void addRelatedRef(String rel, URI uri) {
        ArrayList<HashMap<String,String>> related = (ArrayList<HashMap<String,String>>) this.links.getOrDefault("related", new ArrayList<>());

        HashMap<String,String> item = new HashMap<String,String>();
        item.put("href", uri.toString());
        item.put("rel", rel);

        related.add(item);

        this.links.put("related", related);
    }

    @Override
    public String toString() {
        return "MetadataDataRestEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", visibility='" + visibility + '\'' +
                ", description='" + description + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                ", releaseStatus='" + releaseStatus + '\'' +
                ", systemIdentifier='" + systemIdentifier + '\'' +
                ", documentation=" + documentation +
                ", interfaceSpecification='" + interfaceSpecification + '\'' +
                ", interfaceDescriptionLanguage='" + interfaceDescriptionLanguage + '\'' +
                ", architectureLayer='" + architectureLayer + '\'' +
                ", businessUnit='" + businessUnit + '\'' +
                '}';
    }
}
