package text;

interface Processor {
    String process(String input); //text to be processed
    String description(String source); //such as file, clipboard, web site
    String author();
}
