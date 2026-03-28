import java.util.*;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class dsproject {
    static scorearray[] g;
    static rpgstack stack;
    static scorequeue queue;
    static int tgn;

    public static int getNextInt(Scanner sc) {
        while (true) {
            try { return sc.nextInt(); }
            catch (Exception e) { 
                System.out.println("Invalid input. Enter an integer:");
                sc.next(); 
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. Initial Data
        Scanner sc = new Scanner(System.in);
        System.out.println("--- START SYSTEM ---");
        System.out.println("Enter total games:");
        tgn = getNextInt(sc);
        g = new scorearray[tgn];
        stack = new rpgstack(); stack.arraydec(tgn);
        queue = new scorequeue(); queue.create(100);
        for (int i = 0; i < tgn; i++) {
            g[i] = new scorearray();
            g[i].scoreops(sc);
            g[i].selectionSortMax();
            stack.push(g[i]);
            if (g[i].a != null) {
                for (int j = 0; j < g[i].ps; j++) queue.enqueue(g[i].a[j]);
            }
        }

        // 2. Dashboard Bridge (Connects HTML to Java)
        HttpServer bridge = HttpServer.create(new InetSocketAddress(8080), 0);
        bridge.createContext("/", (ex) -> {
            String q = ex.getRequestURI().getQuery();
            String res = "System Ready! Use the buttons above.";
            
            if (ex.getRequestURI().getPath().equals("/run")) {
                int btn = 0; String id = "";
                if (q != null) {
                    for (String s : q.split("&")) {
                        if (s.startsWith("btn=")) btn = Integer.parseInt(s.split("=")[1]);
                        if (s.startsWith("id=")) id = s.split("=")[1];
                    }
                }
                res = execute(btn, id);
            }

            // Load HTML and replace placeholder
            String page = "";
            try (Scanner s = new Scanner(new File("index.html")).useDelimiter("\\A")) {
                page = s.hasNext() ? s.next() : "";
            } catch (Exception e) { page = "Error: index.html missing"; }
            
            page = page.replace("{{RESULT}}", res);
            ex.sendResponseHeaders(200, page.length());
            OutputStream os = ex.getResponseBody();
            os.write(page.getBytes());
            os.close();
        });

        System.out.println("\n--- CONNECTED ---");
        System.out.println("Go to http://localhost:8080 in your browser.");
        bridge.start();
    }

    static String execute(int b, String val) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(bos));
        try {
            if (b == 1) { for (int i = 0; i < tgn; i++) g[i].lsplayerid(Integer.parseInt(val)); }
            if (b == 2) { 
                highscorell h = new highscorell();
                for (int i = 0; i < tgn; i++) if (g[i].a != null && g[i].a.length > 0) h.insert(g[i].a[0]);
                h.displayhighll(tgn);
            }
            if (b == 3) {
                tournamentresultsll t = new tournamentresultsll();
                for (int i = 0; i < tgn; i++) if (g[i].a != null && g[i].a.length > 0) t.insert(g[i].a[0]);
                t.displayll(tgn);
            }
            if (b == 4) { scorearray r = stack.peek(); if (r != null) for (int i = 0; i < r.ps; i++) System.out.println("ID: " + r.a[i].playerid + " Score: " + r.a[i].sco); }
            if (b == 5) { scorearray u = stack.pop(); if (u != null) System.out.println("Removed Game " + u.gn); }
            if (b == 6) { queue.display(); }
            
            System.out.flush();
            String out = bos.toString();
            try (PrintWriter pw = new PrintWriter(new FileWriter("results.txt", true))) {
                pw.println("Action " + b + ":\n" + out + "---");
            } catch (Exception e) {}
            return out;
        } finally {
            System.setOut(old);
        }
    }
}