<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
%><%@include file="/apps/slicedemo/global.jsp"
%><slice:lookup var="model" type="<%=com.cognifide.demo.slice.demo.core.models.HelloWorldModel.class%>"/>
<c:if test="${not empty model.text}">
	<p>Text property: ${model.text}</p>
</c:if>

<pre>
HelloWorldModel says:
${model.message}
<p><b>Today's winning number is: ${model.dynamicMessage}</b></p>
</pre>