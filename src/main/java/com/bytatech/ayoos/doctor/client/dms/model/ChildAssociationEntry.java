package com.bytatech.ayoos.doctor.client.dms.model;

import java.util.Objects;
import com.bytatech.ayoos.doctor.client.dms.model.ChildAssociation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ChildAssociationEntry
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-02-07T11:14:52.261+05:30[Asia/Calcutta]")

public class ChildAssociationEntry   {
  @JsonProperty("entry")
  private ChildAssociation entry = null;

  public ChildAssociationEntry entry(ChildAssociation entry) {
    this.entry = entry;
    return this;
  }

  /**
   * Get entry
   * @return entry
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public ChildAssociation getEntry() {
    return entry;
  }

  public void setEntry(ChildAssociation entry) {
    this.entry = entry;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChildAssociationEntry childAssociationEntry = (ChildAssociationEntry) o;
    return Objects.equals(this.entry, childAssociationEntry.entry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entry);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChildAssociationEntry {\n");
    
    sb.append("    entry: ").append(toIndentedString(entry)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

