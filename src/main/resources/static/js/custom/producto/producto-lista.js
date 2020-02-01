var productoLista = {
		init: function(){
			this.inicializarBotones();
			this.editar();
			this.eliminar();
			this.realizarOperacion();
		},

		inicializarBotones: function(){
			$('#agregarProducto').on('click', function(){
				var url = contextUrl + 'producto/alta/';
				window.location.href = url;
			});
		},

		editar: function() {
			$('.editar-producto').on('click', function(){
				var registroProducto = $(this).closest('tr');
				var idProducto = $(registroProducto).data('id');

				var url = contextUrl + 'producto/edit?idProducto=' + idProducto;
				window.location.href = url;
			});
		},
		
		eliminar: function() {
			$('.eliminar-producto').on('click', function(){
				var registroProducto = $(this).closest('tr');
				var idProducto = $(registroProducto).data('id');

				$('#idProductoEliminar').val(idProducto);
				$('#modalEliminarProducto').modal('show');
			});

			$('#confirmaEliminaProducto').on('click', function() {

				$('#modalEliminarProducto').modal('hide');
				$('#_method').val('delete');

				var url = contextUrl + 'producto/' +  $('#idProductoEliminar').val();

				$('#formProductoEliminar').attr('action', url);
				$('#formProductoEliminar').submit();

			});
		},

		realizarOperacion: function() {
			$('.operacion-producto').on('click', function(){
				var registroProducto = $(this).closest('tr');
				var idProducto = $(registroProducto).data('id');

				$('#idProductoOperacion').val(idProducto);
				$('#modalOperacion').modal('show');
			});
			
			$('#guardarOperacion').on('click', function(){
				var url = contextUrl + 'operacion';

				$('#_method').val('post');
				$('#operacionForm').attr('action', url);	
				$('#operacionForm').submit();
			})
		}
};

$(document).ready(function() {
	productoLista.init();
});