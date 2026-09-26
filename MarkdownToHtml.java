import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner; // ввод

public class MarkdownToHtml {

    public static void main(String[] args) {

        System.out.print("Преобразовать MarkDown ссылки в HTML.\n" +"Буркеев Дмитрий Марсович 25ВП2 14 вариант\n");

        Scanner in = new Scanner(System.in);

        inputMarkdown(in);
        in.close();
    }
    /**
     * проверка вводимых данных на соответствие требованиям
     * @param in сыллка на объект консольнога ввода
     * @throws 33 строка: Если пользователь ввел пустую строку
     * @throws 41 строка: Если введена строка не соответствующая стандарту [Якорь ссылки](http://example.com)
     */
    public static void inputMarkdown(Scanner in) {

        while (true) {

            try {

                System.out.print("Введите MarkDown ссылку: ");

                String markdown = in.nextLine();

                if (markdown.trim().isEmpty())
                    throw new Exception("Введена пустая строка!");

                String regex = "(?<!\\!)\\[([^\\]]+)\\]\\((https?://(?:www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(?:/[^\\)]*)?)\\)";

                Pattern pattern = Pattern.compile(regex);

                Matcher matcher = pattern.matcher(markdown);

                if (!matcher.find())
                    throw new Exception( "Введена некорректная MarkDown ссылка!" );

                linkOutput(matcher);

                return;

            } catch (Exception e) {

                System.out.println("Ошибка. " + e.getMessage());
            }
        }
    }

    /**
     * Вывод результата обработки строки.
     * @param markdown найденная Markdown-ссылка
     */
    public static void linkOutput(Matcher markdown) {

        StringBuffer result = new StringBuffer();

        do {

            String text = markdown.group(1);
            String url = markdown.group(2);

            String html = "<a href=\"" + url + "\">" + text;

            result.append(html);

        } while (markdown.find());

        System.out.println("Результат:");
        System.out.println(result);
    }
}