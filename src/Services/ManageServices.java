package Services;

import java.io.IOException;

public class ManageServices {
    private ServiceSearch search = new ServiceSearch();
    private ServiceEdit edit = new ServiceEdit();
    private ServiceDelete delete = new ServiceDelete();
    private ServiceAdd add = new ServiceAdd();

    public void search() throws IOException {
        search.searchService();
    }

    public void edit() throws IOException {
        edit.editService();
    }

    public void delete() throws IOException {
        delete.deleteService();
    }

    public void add() throws IOException {
        add.addService();
    }
}
