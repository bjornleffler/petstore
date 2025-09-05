package org.openapitools.api;

import org.openapitools.model.ModelApiResponse;
import org.openapitools.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

// Added by Bjorn.
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ConcurrentHashMap;


/**
 * A delegate to be called by the {@link PetApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public interface PetApiDelegate {

    // Added by Bjorn.
    Map<Long, Pet> pets = new ConcurrentHashMap<>();
    AtomicLong idCounter = new AtomicLong();

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /pet : Add a new pet to the store
     * 
     *
     * @param pet Pet object that needs to be added to the store (required)
     * @return successful operation (status code 200)
     *         or Invalid input (status code 405)
     * @see PetApi#addPet
     */
    default ResponseEntity<Pet> addPet(Pet pet) {
        System.out.println("BJORN Add pet. Name: " + pet.getName() + " Status: " + pet.getStatus());
        long id = idCounter.incrementAndGet();
        pet.setId(id);
        pets.put(id, pet);
        return ResponseEntity.ok(pet);
    }

    /**
     * DELETE /pet/{petId} : Deletes a pet
     * 
     *
     * @param petId Pet id to delete (required)
     * @param apiKey  (optional)
     * @return Invalid pet value (status code 400)
     * @see PetApi#deletePet
     */
    default ResponseEntity<Void> deletePet(Long petId, String apiKey) {
        System.out.println("BJORN Delete pet. ID: " + petId);
        if (pets.get(petId) == null) {
            return ResponseEntity.notFound().build();
        }
        pets.remove(petId);
        return ResponseEntity.ok().build();
    }

    /**
     * GET /pet/findByStatus : Finds Pets by status
     * Multiple status values can be provided with comma separated strings
     *
     * @param status Status values that need to be considered for filter (required)
     * @return successful operation (status code 200)
     *         or Invalid status value (status code 400)
     * @see PetApi#findPetsByStatus
     */
    default ResponseEntity<List<Pet>> findPetsByStatus(List<String> status) {
        System.out.println("BJORN Find pets by status: " + status);
        Collection<Pet> values = pets.values();
        if (values.isEmpty()) {
            List<Pet> emptyList = List.of();
            return ResponseEntity.ok(emptyList);
        }
        List<Pet> result = values.stream()
                .filter(p -> status.contains(p.getStatus().toString()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    /**
     * GET /pet/findByTags : Finds Pets by tags
     * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
     *
     * @param tags Tags to filter by (required)
     * @return successful operation (status code 200)
     *         or Invalid tag value (status code 400)
     * @deprecated
     * @see PetApi#findPetsByTags
     */
    @Deprecated
    default ResponseEntity<List<Pet>> findPetsByTags(List<String> tags) {
        System.out.println("BJORN Find pets by tags: " + tags);
        Collection<Pet> values = pets.values();
        if (values.isEmpty()) {
            List<Pet> emptyList = List.of();
            return ResponseEntity.ok(emptyList);
        }
        List<Pet> result = values.stream()
                .filter(p -> p.hasTags(tags))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    /**
     * GET /pet/{petId} : Find pet by ID
     * Returns a single pet
     *
     * @param petId ID of pet to return (required)
     * @return successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Pet not found (status code 404)
     * @see PetApi#getPetById
     */
    default ResponseEntity<Pet> getPetById(Long petId) {
        Pet pet = pets.get(petId);
        System.out.println("BJORN Get pet by ID " + petId + " Result: " + pet);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }

    /**
     * PUT /pet : Update an existing pet
     * 
     *
     * @param pet Pet object that needs to be added to the store (required)
     * @return successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Pet not found (status code 404)
     *         or Validation exception (status code 405)
     * API documentation for the updatePet operation
     * @see <a href="http://petstore.swagger.io/v2/doc/updatePet">Update an existing pet Documentation</a>
     * @see PetApi#updatePet
     */
    default ResponseEntity<Pet> updatePet(Pet input) {
        Pet pet = pets.get(input.getId());
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("BJORN Update pet with ID " + input.getId() + " Name: " + input.getName() + " Status: " + input.getStatus());
        pet.setName(input.getName());
        pet.setCategory(input.getCategory());
        pet.setPhotoUrls(input.getPhotoUrls());
        pet.setTags(input.getTags());
        pet.setStatus(input.getStatus());
        pets.put(input.getId(), pet);
        return ResponseEntity.ok(pet);
    }

    /**
     * POST /pet/{petId} : Updates a pet in the store with form data
     * 
     *
     * @param petId ID of pet that needs to be updated (required)
     * @param name Updated name of the pet (optional)
     * @param status Updated status of the pet (optional)
     * @return Invalid input (status code 405)
     * @see PetApi#updatePetWithForm
     */
    default ResponseEntity<Void> updatePetWithForm(Long petId,
        String name,
        String status) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /pet/{petId}/uploadImage : uploads an image
     * 
     *
     * @param petId ID of pet to update (required)
     * @param additionalMetadata Additional data to pass to server (optional)
     * @param file file to upload (optional)
     * @return successful operation (status code 200)
     * @see PetApi#uploadFile
     */
    default ResponseEntity<ModelApiResponse> uploadFile(Long petId,
        String additionalMetadata,
        MultipartFile file) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 0, \"type\" : \"type\", \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
