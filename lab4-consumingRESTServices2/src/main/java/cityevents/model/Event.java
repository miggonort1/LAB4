package cityevents.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "description",
    "organizer",
    "category",
    "location",
    "date",
    "price",
    "fqas"
})
@Generated("jsonschema2pojo")
public class Event {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("organizer")
    private String organizer;
    @JsonProperty("category")
    private String category;
    @JsonProperty("location")
    private String location;
    @JsonProperty("date")
    private String date;
    @JsonProperty("price")
    private String price;
    @JsonProperty("fqas")
    private Object fqas;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Event() {}
    
    public Event(String id, String name, String description, String organizer, String category, String location, String date, String price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.organizer = organizer;
		this.category = category;
		this.location = location;
		this.date = date;
		this.price = price;
    }

	public Event(String eventName, String eventDescription, String eventOrganizer, String eventCategory,
			String eventLocation, String eventDate, String eventPrice) {
		// TODO Auto-generated constructor stub
	}
	
	public Event(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("organizer")
    public String getOrganizer() {
        return organizer;
    }

    @JsonProperty("organizer")
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("fqas")
    public Object getFqas() {
        return fqas;
    }

    @JsonProperty("fqas")
    public void setFqas(Object fqas) {
        this.fqas = fqas;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}