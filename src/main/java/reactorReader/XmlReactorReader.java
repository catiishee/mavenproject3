/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reactorReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import react.Reactor;
import react.ReactorSource;

/**
 *
 * @author user
 */
public class XmlReactorReader implements Reader {

    @Override
    public List<Reactor> read(File file) throws IOException {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("reactor", Reactor.class);
        xstream.alias("req", List.class);
        xstream.allowTypes(new Class<?>[]{Reactor.class});

        try (FileReader reader = new FileReader(file)) {
            List<Reactor> reactors = (List<Reactor>) xstream.fromXML(reader);
            for (Reactor reactor : reactors) {
                reactor.setSource(ReactorSource.XML);
            }
            return reactors;
        }
    }
}


