<%@ include file="common/headers.jspf"%>
<%@ include file="common/navigation.jspf"%>

	<div class="container">
		<h2>Your Todos</h2>
		<table class="table">
			<thead>
				<tr>
					<!-- <th>id</th> -->
					<th>Description</th>
					<th>Target Date</th>
					<th>Is Done</th>
					<th></th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.description}</td>
						<td>${todo.target}</td>
						<td>${todo.done}</td>
						<td><a href="/update-todo?id=${todo.id}"
							class="btn btn-primary">Update</a></td>
						<td><a href="/delete-todo?id=${todo.id}"
							class="btn btn-danger">Delete</a></td>
					</tr>
			</c:forEach>
			</tbody>
		</table>
		<a href="/add-todo" class="btn btn-success">Add Todo</a>
	</div>
	<%@ include file="common/footers.jspf"%>
