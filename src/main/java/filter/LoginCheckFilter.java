package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.User;

/**
 * ログインチェックフィルタクラス
 */
@WebFilter("/*")
public class LoginCheckFilter extends HttpFilter {
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		// リクエストURLを取得
		String requestURL = request.getRequestURI();
		
		// セッション情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
				
		if(requestURL.contains("/css/")
				|| requestURL.contains("/images/")
				|| requestURL.equals(request.getContextPath() + "/")
				|| requestURL.endsWith("/login")
				|| requestURL.contains("/product/list")
				|| requestURL.contains("/product/detail")
				|| requestURL.endsWith("/legal")
				|| requestURL.endsWith("/company")
				|| requestURL.endsWith("/contact")
				|| requestURL.endsWith("/privacyPolicy")
				|| requestURL.contains("/reset/password")
				|| requestURL.contains("/register/user")) {
			chain.doFilter(request, response);
			return;
		}
		
		// ログイン済みならそのまま
		if(loginUser != null) {
			chain.doFilter(request, response);
		} else {
			 // 未ログインならログイン画面へ
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
}
