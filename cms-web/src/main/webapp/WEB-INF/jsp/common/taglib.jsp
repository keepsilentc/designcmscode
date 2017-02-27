<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath %>"/>
<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-datetimepicker.min.css" />
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/jquery-ui.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/js/angular.min.js"></script>
<script src="${ctx}/js/ng-file-upload.min.js"></script>
<script src="${ctx}/js/angular-cookies.min.js"></script>
<script src="${ctx}/js/autocomplete.js"></script>

<script type="text/javascript">
	var ctx = "${ctx}";
</script>
