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
    "question",
    "answer"
})
@Generated("jsonschema2pojo")
public class Fqas {

    @JsonProperty("id")
    private String id;
    @JsonProperty("question")
    private String question;
    @JsonProperty("answer")
    private String answer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Fqas() {}

	public Fqas(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public Fqas(String id, String question, String answer) {
		this.id = id;
		this.question = question;
		this.answer = answer;
	}

	@JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    @JsonProperty("question")
    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonProperty("answer")
    public String getAnswer() {
        return answer;
    }

    @JsonProperty("answer")
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		String id = "Id: " + this.getId() + "\n";
		String question = "Question: " + this.getQuestion() + "\n";
		String answer = "Answer: " + this.getAnswer() + "\n";
		return id + question + answer;
	}
    
}