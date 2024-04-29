/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package reactorReader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import react.Reactor;

/**
 *
 * @author user
 */
public interface Reader {
    public List<Reactor> read(File file) throws IOException;
            
}
