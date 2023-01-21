<%@ include file="common/headers.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">

	<h2>Enter New Todo details</h2>
	<hr>
	<form:form method="POST" modelAttribute="todo">

		<fieldset class="form-group" class="mb-3" style="margin: 10px">
			<form:label path="description">Description : </form:label>
			<form:input type="text" path="description" required="required" />
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group" class="mb-3" style="margin: 10px">
			<form:label path="target">Target Date : </form:label>
			<form:input type="text" path="target" required="required" />
			<form:errors path="target" cssClass="text-warning" />
		</fieldset>

		<form:input type="hidden" path="id" />
		<form:input type="hidden" path="done" />

		<input type="submit" class="btn btn-success" style="margin: 10px">

	</form:form>
</div>
<%@ include file="common/footers.jspf"%>
<script type="text/javascript">
	$('#target').datepicker({
		format : 'yyyy-mm-dd'
	});
</script>


