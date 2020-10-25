# :telescope: Trinity Mars

App de visualização de imagens da sonda Curiosity em Marte, utiliza imagens do banco de dados da NASA

## :speech_balloon: Instruções de uso

Para utilização do App basta criar uma conta e fazer login utilizando os mesmos dados. As informações de cadastro e login são enviadas e processadas pelo Firebase. Os e-mails de cadastro devem ter um formato válido e as senhas devem conter entre 8 e 16 caracteres, pelo menos uma letra minúscula, uma maiúscula, números e caracteres especiais.

### Feito utilizando
1. [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
1. [Clean Architecture](https://pusher.com/tutorials/clean-architecture-introduction)
1. [Room](https://developer.android.com/topic/libraries/architecture/room)
1. [Koin](https://insert-koin.io)
1. [MVVM Architecture](https://developer.android.com/topic/libraries/architecture/viewmodel)
1. [Glide](https://github.com/bumptech/glide)
1. [Android Material Design](https://developer.android.com/guide/topics/ui/look-and-feel?hl=pt)
1. [Retrofit](https://square.github.io/retrofit/)
1. [Firebase Authentication](https://firebase.google.com/docs/auth)
1. [Mockito](https://site.mockito.org)

### Possui:
* Coroutines integradas com o Room para chamadas assíncronas ao banco de dados e para a API usando Retrofit
* Injeção de dependência usando o Koin
* Tests unitários para chamadas ao banco de dados e para a API
* Arquitetura View Model e separação de responsabilidades
* Design fluído com base nas orientações do Material Design do Google
* Validação nos inputs do usuário
* Armazenamento da sessão de login usando SharedPreferences, com revogação ao clicar no botão Sair
* Cache de dados da API utilizando o Room, possibilidade de visualização de dados offline
* Animações para transições de tela

### Próximos passos:
* Maior abrangência de testes unitários
* Testes de integração
* Maior detalhamento de imagens ao clicar sobre elas
* Imagens de outras sondas espaciais
* Função de busca
* Melhorias na estabilidade e na proteção contra crashs e exceptions

### :camera: Imagens
![Mainr](https://i.imgur.com/hS6qmf9.png) ![Main2r](https://i.imgur.com/igzCKv1.png) ![Main3r](https://i.imgur.com/nubRjbQ.png) ![Main4r](https://i.imgur.com/vdmuWlM.png)
