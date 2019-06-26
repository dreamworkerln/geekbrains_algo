package ru.geekbrains.algo.deque;

import ru.geekbrains.algo.deque.entities.DequeDLL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args) {

        new App();
    }

    App() {

        invertText();
    }


    /**
     * In  file:  data/sample.md
     * Out file:  data/output.md
     */
    void invertText() {

        try{

            Collection<?> collection = new ArrayList<>();

            Deque<Character> deq = new DequeDLL<>();

            try(BufferedReader reader = Files.newBufferedReader(Paths.get("data/sample.md"));
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("data/output.md"))) {

                StringBuilder sb = new StringBuilder();
                Iterator<Character> it;


                String line;
                while ((line = reader.readLine()) != null){

                    deq.clear();
                    sb.setLength(0);

                    for (int i = 0; i < line.length(); i++) {
                        deq.addFirst(line.charAt(i));
                    }

                    it = deq.iterator();
                    while (it.hasNext()) {
                        sb.append(it.next());
                    }
                    writer.write(sb.toString());
                    writer.write(String.format("%n"));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}
