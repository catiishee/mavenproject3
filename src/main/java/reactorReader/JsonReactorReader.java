/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reactorReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import react.Reactor;
import react.ReactorSource;

/**
 *
 * @author user
 */
public class JsonReactorReader implements Reader {

    @Override
    public List<Reactor> read(File file) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Reactor>>() {}.getType();
        try (FileReader reader = new FileReader(file)) {
            List<Reactor> reactors = gson.fromJson(reader, type);
            for (Reactor reactor : reactors) {
                reactor.setSource(ReactorSource.JSON);
            }
            return reactors;
        }

    }
}
