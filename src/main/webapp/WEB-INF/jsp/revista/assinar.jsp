<div>
	<input type="hidden" name="revista.id" value="${revista.id}" />
	
	<c:if test="${usuarioLogado.logado}">
	
		<input type="hidden" name="usuarioLogado.usuarioInfo.email" value="${usuarioLogado.usuarioInfo.email}" />
		<form action="${linkTo[RevistaController].assinarLogado[revista]}"method="POST">
		<label>Confirmação de email: </label>
		<input type="text" class="form-control" name="usuarioLogado.usuarioInfo.email" value="${usuarioLogado.usuarioInfo.email}" />
			<button type="submit" class="btn btn-default">Salvar</button>
			<a href="${linkTo[RevistaController].index}">Cancelar</a>
		</form>
		<%-- <% 
	String id = request.getParameter("revista.id");
	response.sendRedirect("/revistaonline/revista/assinarlogado/" + id); 
	%> --%>
	</c:if>

	<c:if test="${!usuarioLogado.logado}">
	<form action="${linkTo[RevistaController].assinarRevista[revista]}"method="POST">
		
			<fieldset>
				<legend>Adicionar email</legend>
				<p>Informe seu email para receber informações da revista.</p>
				Email: <input type="text" name="email" /><br> Nome: <input
					type="text" name="nome" /><br>
				<button type="submit" class="btn btn-default">Salvar</button>
			</fieldset>
		
	</form>
	</c:if>
	
	
</div>