package util;

import com.nhncorp.lucy.security.xss.XssPreventer;
import org.springframework.stereotype.Component;

@Component
public class XSSFilter {
    public String escape(String originalString){
        return XssPreventer.escape(originalString); // => "&quot;&gt;&lt;script&gt;alert(&#39xss&#39);&lt;/script&gt;"
    }

    public String unescape(String cleanString){
        return XssPreventer.unescape(cleanString); // => "\"><script>alert('xss');</script>"
    }
}
