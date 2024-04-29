/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chain;

import java.io.File;
import java.io.IOException;
import java.util.List;
import react.Reactor;
import reactorReader.YamlReactorReader;

/**
 *
 * @author user
 */
public class YamlReaderHandler extends BaseReaderHandler{

    @Override
    public List<Reactor> handle(File file) throws IOException {
        return handle(file, new YamlReactorReader(), ".yaml");
    }
    
}
