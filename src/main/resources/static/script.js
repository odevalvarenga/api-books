const apiUrl = "/books";

function mostrarPagina(id) {

    const paginas = document.querySelectorAll(".pagina");

    paginas.forEach(pagina => {

        pagina.style.display = "none";

    });

    document.getElementById(id).style.display = "block";
}

/* =========================
   TROCAR PÁGINAS
========================= */

function mostrarPagina(id) {

    document.getElementById("paginaBiblioteca")
        .style.display = "none";

    document.getElementById("paginaBusca")
        .style.display = "none";

    document.getElementById("paginaNovoLivro")
        .style.display = "none";

    document.getElementById(id)
        .style.display = "block";
}



/* =========================
   LISTAR LIVROS
========================= */

async function carregarLivros() {

    mostrarPagina("paginaBiblioteca");

    try {

        const resposta = await fetch(apiUrl, {

            method: "GET",

            headers: {
                "x-api-key": "123456"
            }
        });

        const dados =
            await resposta.json();

        const livros =
            dados.content;

        const lista =
            document.getElementById("listaLivros");

        lista.innerHTML = "";



        if (!Array.isArray(livros)
            || livros.length === 0) {

            lista.innerHTML =
                "<p>Nenhum livro encontrado</p>";

            return;
        }



        livros.forEach(livro => {

            lista.innerHTML += `

                <div class="livro-card">

                    <h3>${livro.title}</h3>

                    <p>
                        <strong>ID:</strong>
                        ${livro.id}
                    </p>

                    <p>
                        <strong>Autor:</strong>
                        ${livro.author?.name || "Não informado"}
                    </p>

                    <p>
                        <strong>Categoria:</strong>
                        ${livro.category?.name || "Não informado"}
                    </p>

                    <p>
                        <strong>Editora:</strong>
                        ${livro.publisher?.name || "Não informado"}
                    </p>

                </div>
            `;
        });

    } catch (erro) {

        console.log(erro);

        alert("Erro ao carregar livros");
    }
}



/* =========================
   CRIAR LIVRO
========================= */

async function criarLivro() {

    const titulo =
        document.getElementById("titulo").value;



    if (titulo.trim() === "") {

        alert("Digite um título");

        return;
    }



    try {

        const resposta = await fetch(apiUrl, {

            method: "POST",

            headers: {

                "Content-Type": "application/json",

                "x-api-key": "123456",

                "Idempotency-Key":
                    crypto.randomUUID()
            },

            body: JSON.stringify({

                title: titulo,

                author: {
                    id: 1
                },

                category: {
                    id: 1
                },

                publisher: {
                    id: 1
                }
            })
        });



        if (!resposta.ok) {

            alert("Erro API");

            return;
        }



        alert("Livro criado!");

        document.getElementById("titulo").value = "";

        carregarLivros();

    } catch (erro) {

        console.log(erro);

        alert("Erro de conexão");
    }
}



/* =========================
   BUSCAR
========================= */

async function buscarLivro() {

    const texto =
        document.getElementById("pesquisa").value;

    mostrarPagina("paginaBiblioteca");

    try {

        const resposta = await fetch(

            `/books/search?title=${texto}`,

            {
                method: "GET",

                headers: {
                    "x-api-key": "123456"
                }
            }
        );



        const livros =
            await resposta.json();

        const lista =
            document.getElementById("listaLivros");

        lista.innerHTML = "";



        if (!Array.isArray(livros)
            || livros.length === 0) {

            lista.innerHTML =
                "<p>Nenhum resultado</p>";

            return;
        }



        livros.forEach(livro => {

            lista.innerHTML += `

                <div class="livro-card">

                    <h3>${livro.title}</h3>

                    <p>
                        <strong>ID:</strong>
                        ${livro.id}
                    </p>

                    <p>
                        <strong>Autor:</strong>
                        ${livro.author?.name || "Não informado"}
                    </p>

                    <p>
                        <strong>Categoria:</strong>
                        ${livro.category?.name || "Não informado"}
                    </p>

                    <p>
                        <strong>Editora:</strong>
                        ${livro.publisher?.name || "Não informado"}
                    </p>

                </div>
            `;
        });

    } catch (erro) {

        console.log(erro);

        alert("Erro ao buscar livros");
    }
}

/* PÁGINA INICIAL */

window.onload = () => {

    mostrarPagina("paginaBiblioteca");

    carregarLivros();
};

carregarLivros();