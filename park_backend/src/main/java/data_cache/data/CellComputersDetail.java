package data_cache.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * List of cell computers
 */
public class CellComputersDetail  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("cellComputers")
  @Valid
  private List<CellComputerDetail> cellComputers = null;

  public CellComputersDetail cellComputers(List<CellComputerDetail> cellComputers) {
    this.cellComputers = cellComputers;
    return this;
  }

  public CellComputersDetail addCellComputersItem(CellComputerDetail cellComputerItem) {
    if (this.cellComputers == null) {
      this.cellComputers = new ArrayList<>();
    }
    this.cellComputers.add(cellComputerItem);
    return this;
  }

  /**
   * Get cellComputers
   * @return cellComputers
  */
  @Valid
  public List<CellComputerDetail> getCellComputers() {
    return cellComputers;
  }

  public void setCellComputers(List<CellComputerDetail> cellComputers) {
    this.cellComputers = cellComputers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CellComputersDetail cellComputerDetailConfigurations = (CellComputersDetail) o;
    return Objects.equals(this.cellComputers, cellComputerDetailConfigurations.cellComputers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cellComputers);
  }

  @Override
  public String toString() {
    return "CellComputers{" +
            "cellComputers=" + cellComputers +
            '}';
  }
}

