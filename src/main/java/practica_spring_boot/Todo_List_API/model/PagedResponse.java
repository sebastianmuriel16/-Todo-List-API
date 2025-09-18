package practica_spring_boot.Todo_List_API.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedResponse<T> {

    private List<T> data;
    private int page;
    private int limit;
    private long total;
}
