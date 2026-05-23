const API_URL = "http://localhost:8080/books"

const API_KEY = "123456"

async function listarLivros(){

    const response = await fetch(API_URL,{

        headers:{
            "x-api-key": API_KEY
        }
    })

    const livros = await response.json()

    const container =
        document.getElementById("books-container")

    container.innerHTML = ""

    livros.content.forEach(livro => {

        container.innerHTML += `

            <div class="book-card">

                <div class="book-info">

                    <h3>${livro.title}</h3>

                    <p>ID: ${livro.id}</p>

                </div>

                <button
                    class="delete-btn"
                    onclick="deletarLivro(${livro.id})">

                    Excluir

                </button>

            </div>
        `
    })
}

async function criarLivro(){

    const title =
        document.getElementById("title").value

    if(title === ""){
        alert("Digite um título")
        return
    }

    const response = await fetch(API_URL,{

        method:"POST",

        headers:{
            "Content-Type":"application/json",
            "x-api-key": API_KEY,
            "Idempotency-Key":
                Date.now().toString()
        },

        body: JSON.stringify({

            title:title,

            author:{
                id:1
            },

            category:{
                id:1
            },

            publisher:{
                id:1
            }
        })
    })

    if(response.ok){

        document.getElementById("title").value = ""

        listarLivros()

    }else{

        const erro = await response.json()

        alert(erro.message || erro.erro)
    }
}

async function deletarLivro(id){

    await fetch(`${API_URL}/${id}`,{

        method:"DELETE",

        headers:{
            "x-api-key": API_KEY
        }
    })

    listarLivros()
}

listarLivros()