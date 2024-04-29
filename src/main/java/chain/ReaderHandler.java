/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package chain;

import java.io.File;
import java.io.IOException;
import java.util.List;
import react.Reactor;
import reactorReader.Reader;

/**
 *
 * @author user
 */
public interface ReaderHandler {

    public abstract List<Reactor> handle(File file) throws IOException;

    public void setNext(ReaderHandler handler);

}
