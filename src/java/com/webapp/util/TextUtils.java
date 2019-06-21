/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.util;

import com.webapp.checker.XMLSyntaxChecker;
/**
 *
 * @author stephen
 */
public class TextUtils {
	
	private static final String scriptTagExpression = "<script.*?</script>";
	private static final String styleTagExpression = "<style.*?</style>";
	
	public static String refineHtml(String src) {
		XMLSyntaxChecker xmlsc = new XMLSyntaxChecker();
		src = xmlsc.check(src);
		return src; 
	}
	
	public static String subStringHtml(String html, int begin, int end) {
		return html.substring(begin, end);
	}
	
	public static String removeUnusedTag(String html) {
		return html.replaceAll(scriptTagExpression, "")
								.replaceAll(styleTagExpression, "");
	}
	
//	public static String removeMiscellaneousTags(String src) {
//		String result = src;
//		String[] expressions = {"<script.*?</script>", "<!--.*?-->", "&nbsp;?", "<style.*?</style>"};
//		for (int i = 0; i < expressions.length;i++) {
//			result = result.replaceAll(expressions[i], "");
//		}
//		return result;
//	}
	
//	public String getHtmlDocsBody(String urlString) throws MalformedURLException, IOException {
//		  String document = "", expression = "<body.*?</body>";
//			URL url = new URL(urlString);
//			URLConnection connection = url.openConnection();
//			connection.addRequestProperty("User-Agent", "Unknown");
//			InputStream is = connection.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//			document = reader.lines().collect(Collectors.joining());
//		  Pattern pattern = Pattern.compile(expression);
//			Matcher matcher = pattern.matcher(document);
//			if (matcher.find()) {
//				document = matcher.group();
//			}
//			reader.close();
//			return document;
//	}
	
}
