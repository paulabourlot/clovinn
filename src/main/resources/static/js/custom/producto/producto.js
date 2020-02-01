var producto = {
		init: function(){
			this.inicializarBotones();
		},

		inicializarBotones: function(){
			$('#volverProducto').on('click', function(){
				var url = contextUrl + 'producto/';
				window.location.href = url;
			});

			$('#guardarProducto').on('click', function(){
				var url = contextUrl + 'producto';

				$('#formProducto').attr('action', url);	
				$('#formProducto').submit();
			});
		},
};

$(document).ready(function() {
	producto.init();
});