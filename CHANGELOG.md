Changelog
===

### 27.03.2023
- Added Orchestra entities
- Added Oauth entities
- Class cleanup and renaming
- Fixed spotbugs errors
- All objects are now copied before assigning
- Added semantic release versioning

### 19.07.2022
- Added Vote entities with rating value

### 14.07.2022
- Updated dependencies
- Added readme and changelog types

### 9.2.2022
- Changed taxonomy tree urn item type to JsonApiRestResponseWrapper.URN_ELEMENT. Fixed mapping error between url and title in TaxonomyTreeDataRestEntity

### 4.2.2022
- Added reference type to taxonomy URN. Enables linking between deprecated and active classifications

### 8.10.2021
- First public release

### 15.10.2021
- v2.0 Breaking changes in POST body. The data object is now inside of a data:{} wrapper. In the SNAPSHOT versions this will be introduced gradually