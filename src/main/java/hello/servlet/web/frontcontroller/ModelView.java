package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class ModelView {
    @NonNull
    private String viewName; //logic name
    private Map<String, Object> model = new HashMap<>();
}
