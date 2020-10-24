# trinityApp

App de visualização de imagens da sonda Curiosity em Marte, API da Nasa

Feito utilizando
1. Coroutines
2. Clean Architecture
3. MVVM Architecure
4. Room
5. Glide
6. Android Material Design
7. Retrofit
8. Koin
9. Firebase Authentication
10. Mockito

Possui:
1 - Coroutines integradas com o Room para chamadas assíncronas ao banco de dados e para a API usando Retrofit
2 - Injeção de dependência usando o Koin
3 - Tests unitários para chamadas ao banco de dados e para a API
4 - Arquitetura View Model e separação de responsabilidades
5 - Design fluído com base nas orientações do Material Design do Google
6 - Validação nos inputs do usuário
7 - Armazenamento da sessão de login usando SharedPreferences, com revogação ao clicar no botão Sair
8 - Cache de dados da API utilizando o Room, possibilidade de visualização de dados offline
