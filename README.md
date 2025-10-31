 Proyecto ON TOUR: Giras de Estudio | Evaluación Parcial 2

 Información del Equipo y Docente

Integrantes del Equipo: Fabiola Villagra y Camila Vera
Carrera: Ingeniería en Informática, 4º Semestre
Sede Duoc UC Antonio Varas 
Docente a Cargo: Diego Cares
Proyecto Base,Gestión de Viajes Académicos App Moviles 

Cumplimiento de Requisitos de la Evaluación Funcional

El proyecto se desarrolló con arquitectura MVVM (Model-View-ViewModel) y verifica todos los puntos solicitados en el encargo.
Requisito,Implementación y Evidencia
Diseño visual con Material 3,"Implementado con el tema personalizado (Orange/Yellow), MaterialCardView, Snackbar para errores (animación), y FAB para la acción principal."
Formularios validados,"Implementado en LoginActivity.kt con validación de formato de email, longitud de contraseña (mín. 6 caracteres) y validación de credenciales fijas."
Navegación funcional,Flujo completo de tres actividades: Login → Home → Aporte.
Gestión de estado,Implementado en GiraViewModel.kt. La HomeActivity observa el progreso financiero ($450k/$750k) y actualiza el estado después del aporte simulado.
Almacenamiento local,Lógica de FileProvider implementada en AndroidManifest.xml y AporteActivity.kt para simular el guardado seguro del comprobante de pago.
Uso de recursos nativos,Implementado el llamado al Intent de la Cámara en AporteActivity.kt. Se utiliza una simulación en el click del botón para garantizar la prueba en emuladores sin fallos.
Animaciones,Implementadas con: 1. ObjectAnimator para la barra de progreso en HomeActivity. 2. Animación de deslizamiento del Snackbar en LoginActivity.

Flujo de Prueba Rápida

Para verificar el funcionamiento y los requisitos de Gestión de Estado y Animaciones:

Email:fabiolavillagra@gmail.com
Contraseña:123456

Secuencia de Prueba:

    Validar Login: Inicie sesión con las credenciales correctas.

    Verificar Home y Animación: La pantalla de bienvenida mostrará "¡Bienvenida, Fabiola Villagra!" y la barra de progreso se animará al 60% (Estado Inicial).

    Probar Gestión de Estado:

        Haga clic en el FAB ("Nuevo Aporte").

        Haga clic en el botón "Tomar Foto del Comprobante" (dispara la simulación).

        Haga clic en "Simular Registro de Aporte".

    Resultado Esperado: La Home se actualizará (Gestión de Estado) a $550.000 y el progreso total se animará al 73.3%.

    
