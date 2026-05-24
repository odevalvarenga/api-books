const apiUrl = "/books";


// =========================
// LISTAR LIVROS
// =========================

async function carregarLivros() {

    try {

        const resposta = await fetch(apiUrl, {

            method: "GET",

            headers: {
                "x-api-key": "123456"
            }
        });

        const dados = await resposta.json();

        const livros = dados.content;

        const lista =
            document.getElementById("listaLivros");

        lista.innerHTML = "";

        if (!Array.isArray(livros)) {

            lista.innerHTML =
                "<p>Nenhum livro encontrado</p>";

            return;
        }

        livros.forEach(livro => {

            lista.innerHTML += `

                <div class="livro-card">

                    <h3>${livro.title}</h3>

                    <p>ID: ${livro.id}</p>

                </div>
            `;
        });

    } catch (erro) {

        console.log(erro);

        alert("Erro ao carregar livros");
    }
}



// =========================
// CRIAR LIVRO
// =========================

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

            const erro =
                await resposta.text();

            console.log(erro);

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



// =========================
// BUSCAR LIVRO
// =========================

async function buscarLivro() {

    const texto =
        document.getElementById("pesquisa").value;

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

        if (!Array.isArray(livros)) {

            lista.innerHTML =
                "<p>Nenhum resultado</p>";

            return;
        }

        livros.forEach(livro => {

            lista.innerHTML += `

                <div class="livro-card">

                    <h3>${livro.title}</h3>

                    <div class="info">
                        <strong>ID:</strong> ${livro.id}
                    </div>

                    <div class="info">
                        <strong>Autor:</strong>
                        ${livro.author?.name || "Não informado"}
                    </div>

                    <div class="info">
                        <strong>Categoria:</strong>
                        ${livro.category?.name || "Não informado"}
                    </div>

                    <div class="info">
                        <strong>Editora:</strong>
                        ${livro.publisher?.name || "Não informado"}
                    </div>

                    <span class="tag">
                        Livro
                    </span>

                </div>
            `;
        });

    } catch (erro) {

        console.log(erro);

        alert("Erro ao buscar livros");
    }
}


carregarLivros();