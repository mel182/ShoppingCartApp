package Model;

import javafx.scene.control.TableColumn;

public class TableColumnReference
{
    private TableColumn tableColumn;
    private String referenceID;


    public TableColumnReference(TableColumn tableColumn, String referenceID) {
        this.tableColumn = tableColumn;
        this.referenceID = referenceID;
    }


    public TableColumn getTableColumn() {
        return tableColumn;
    }

    public String getReferenceID() {
        return referenceID;
    }
}
