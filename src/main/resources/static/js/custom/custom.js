var opcionesCantidad = {aSep: '', aDec: '.', vMin: '0', vMax: '999999.999'};
var opcionesImporte = {aSep: '', aDec: '.', vMin: '0', vMax: '999999.999'};

var funcionesComunes = {
		init: function() {
			this.validarFormulario();
			this.mensajesValidacion();
			this.minimizarTextosLargos();
			this.ocultarMensajes();
			this.transformarMayusculas();
			this.valorPorDefectoImportes();
			this.inicializarModales();
			this.controlPaginacion();
			this.mostrarMensajes();
			this.botonPorDefecto();
			this.botonRefrescoPantalla();
			
			$('.cantidad').autoNumeric('init', opcionesCantidad);
			$('.importe').autoNumeric('init', opcionesImporte);
			$('.campo-fecha').datepicker();
		},

		validarFormulario: function() {
			$('.form-valido').validate ({
				errorClass: 'error-class',
				validClass: 'valid-class',
				errorElement: 'div',
				errorPlacement: function(error, element) {
					if(element.parent('.input-group').length) {
						error.insertAfter(element.parent());
					} else {
						error.insertAfter(element);
					}
				},
				onError : function(){
					$('.input-group.error-class').find('.help-block.form-error').each(function() {
						$(this).closest('.form-group').addClass('error-class').append($(this));
					});
				}
			});
		},

		mensajesValidacion: function() {
			jQuery.extend(jQuery.validator.messages, {
				required: 'Este campo es obligatorio.',
				remote: 'Por favor, rellena este campo.',
				email: 'Por favor, escribe una dirección de correo válida',
				url: 'Por favor, escribe una URL válida.',
				date: 'Por favor, escribe una fecha válida.',
				dateISO: 'Por favor, escribe una fecha (ISO) válida.',
				number: 'Por favor, escribe un número entero válido.',
				digits: 'Por favor, escribe sólo dígitos.',
				creditcard: 'Por favor, escribe un número de tarjeta válido.',
				equalTo: 'Por favor, escribe el mismo valor de nuevo.',
				accept: 'Por favor, escribe un valor con una extensión aceptada.',
				maxlength: jQuery.validator.format('Por favor, no escribas más de {0} caracteres.'),
				minlength: jQuery.validator.format('Por favor, no escribas menos de {0} caracteres.'),
				rangelength: jQuery.validator.format('Por favor, escribe un valor entre {0} y {1} caracteres.'),
				range: jQuery.validator.format('Por favor, escribe un valor entre {0} y {1}.'),
				max: jQuery.validator.format('Por favor, escribe un valor menor o igual a {0}.'),
				min: jQuery.validator.format('Por favor, escribe un valor mayor o igual a {0}.')
			});
		},

		minimizarTextosLargos: function(){
			var minimized_elements = $('p.minimize');
			var minimize_character_count = 200;    

			minimized_elements.each(function(){    
				var t = $(this).text();        
				if(t.length < minimize_character_count ) return;

				$(this).html(
						t.slice(0,minimize_character_count)+'<span>... </span><a href="#" class="more">Ver más</a>'+
						'<span style="display:none;">'+ t.slice(minimize_character_count ,t.length) +
						' <a href="#" class="less">Ver menos</a></span>'
				);

			}); 

			$('a.more', minimized_elements).click(function(event){
				event.preventDefault();
				$(this).hide().prev().hide();
				$(this).next().show();        
			});

			$('a.less', minimized_elements).click(function(event){
				event.preventDefault();
				$(this).parent().hide().prev().show().prev().show();    
			});
		},

		ocultarMensajes: function() {
			setTimeout( function() {
				$('#mensaje-exito').fadeOut(1500);
			},3000);
		},

		transformarMayusculas: function() {
			$('input').on('keyup', function(){
				this.value = this.value.toUpperCase();
			});

			$('textarea').on('keyup', function(){
				this.value = this.value.toUpperCase();
			});
		},

		valorPorDefectoImportes: function() {
			$('.importe').focusout(function() {
				var valor = $(this).val();
				if(valor === ''){
					$(this).val('0.00');
				}
			});
		},

		inicializarModales: function() {
			$('.modal').on('shown.bs.modal', function() {
				var campo = $(this).find('[autofocus]');
				$(campo).focus();
				
				funcionesComunes.valorPorDefectoImportes();
				funcionesComunes.inicializarAutonumericos();
				funcionesComunes.botonPorDefecto();
			});
		},
		
		botonPorDefecto: function() {
			$('input.campo-accion').unbind('keypress');
			$('input.campo-accion').on('keypress', function(e) {
				if (e.keyCode == 13) {
					var boton = $(this).parents('.modal').find('button.btn-accion');
					if(boton.length > 0){
						boton.click();
					}
					return false;
				}
			});
			$('select.campo-accion').on('change', function(e) {
				var boton = $(this).parents('.modal').find('button.btn-accion');
				if(boton.length > 0){
					boton.click();
				}
				return false;
			});
		},
		
		inicializarAutonumericos: function() {
			$('.cantidad').autoNumeric('init', opcionesCantidad);
			$('.importe').autoNumeric('init', opcionesImporte);
		},
		
		controlPaginacion: function() {
			$('form.formulario-pageable-get,  form.formulario-pageable-post').on( 'click', 
					'.pagination-control', function(e) { 

				var controlPaginacion = $(this);

				if(controlPaginacion.hasClass('disabled') === false){
					var gotoPage = $('#page').val();

					if(controlPaginacion.hasClass('previousPage')){
						gotoPage--;
					} else {
						gotoPage++;
					}			
					$('#page').val(gotoPage);

					var formulario = $(controlPaginacion).closest('form');
					if (formulario.is('form.formulario-pageable-post') ) {
						$('#_method').val('POST');
						$('form.formulario-pageable-post').submit();
					}
					else {
						$('#_method').val('GET');
						$('form.formulario-pageable-get').submit();
					}					
				}
			});
		},
		
		mostrarMensajes: function() {
			$.notify.defaults({
				position: 'right bottom',
				clickToHide: true,
				autoHide: true
			});
			
			var mensajeExito = $('#mensajeExito').html();
			if(mensajeExito){
				$.notify(mensajeExito, 'success');
			}
			
			var mensajeError = $('#mensajeError').html();
			if(mensajeError){
				$.notify(mensajeError, 'error');
			}
			
			$('li.mensaje-error-validacion').each(function() {
				var mensaje = $(this).html();
				$.notify(mensaje, 'error');
			});
		},
		
		botonRefrescoPantalla: function() {
			$('.refresh').on('click', function() {
				 location.reload();
			});
		}
}

$(function() {
	funcionesComunes.init();
});