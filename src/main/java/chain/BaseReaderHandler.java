/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public abstract class BaseReaderHandler implements ReaderHandler {
    
    protected ReaderHandler next;
    
    public  List<Reactor> handle(File file, Reader reader, String extension) throws IOException{
        if(file.getAbsolutePath().endsWith(extension)){
            return reader.read(file);
        }
        if(next != null){
            return next.handle(file);
        }
        throw new IOException();
    }

    @Override
    public void setNext(ReaderHandler handler){
        next = handler;
    }
    
}
