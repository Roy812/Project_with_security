package springboot.with.security.dto;

//Een Data Transfer Object (DTO) heeft als doel om een objects aan te maken dat geen relaties bevat met andere object (zoals in de domain klassen).
//Het ophalen van een object (via een Endpoint) dat een relatie heeft met een ander object kan een loop veroorzaken,
//het ontvangen object zou dan slecht uitgelezen kunnen worden.
public class addBookingDTO {
    public Long userId;
    public Long lessonId;
    public String title;
}
