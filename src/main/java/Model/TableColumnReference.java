package Model;

import javafx.scene.control.TableColumn;

/**
 * <p>This is the table reference column model class which contain properties for initializing table view</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class TableColumnReference
{
    private TableColumn tableColumn;
    private String referenceID;

    /**
     * Constructor
     * @param tableColumn The table view column
     * @param referenceID The column reference ID
     */
    public TableColumnReference(TableColumn tableColumn, String referenceID) {
        this.tableColumn = tableColumn;
        this.referenceID = referenceID;
    }

    /**
     * Get tabble column
     * @return The table view column
     */
    public TableColumn getTableColumn() {
        return tableColumn;
    }

    /**
     * Get the column property reference ID
     * @return The column reference ID
     */
    public String getReferenceID() {
        return referenceID;
    }
}
