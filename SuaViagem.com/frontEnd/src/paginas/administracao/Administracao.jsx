import React from 'react'
import { Link } from 'react-router-dom'
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import './styleAdministracao.css'
import { ReservaSucesso } from '../../components/detale-produto-reserva/ReservaSucesso';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import RemoveCircleIcon from '@mui/icons-material/RemoveCircle';
import Alert from '@mui/material/Alert';

export default function Administracao() {

    const [confirm, setConfirm] = React.useState(false);
    const [categoria, setCategoria] = React.useState([]);
    const [produto, setProduto] = React.useState([]);
    const [categoriaSelecionada, setCategoriaSelecionada] = React.useState(null);
    const [cidades, setCidades] = React.useState([]);
    const [cidadeSelecionada, setCidadeSelecionada] = React.useState(null);
    const [secondError, setSecondError] = React.useState(false);
    const [formData, setFormData] = React.useState(
        {
            nomeProduto: "", 
            categoriaValue: "", 
            endereco: "", 
            cidade: "", 
            descricao: "",
            normasCasa: '',
            saudeSeguranca: '',
            politicasCancelamento: '',
            pontuacao: '',
            localMapa: '',
            comentarios: ''

        }
    )

    const {nomeProduto, categoriaValue , endereco, cidade,  saudeSeguranca, normasCasa, politicasCancelamento, pontuacao, localMapa, comentarios, descricao} = formData
    
    const [image, setImage ] = React.useState([
        {
        url: ""
        }
    ])

  

    const [caracteristicas, setCaracteristicas ] = React.useState([
        {
        nome: ""
        }
    ])

    function handleAddCaracteristicas(){
        
        setCaracteristicas([...caracteristicas, {nome: ""}])
       }

    function handleRemoveCaracteristicas(index){
        const list = [...caracteristicas]
        list.splice(index, 1);

        setCaracteristicas(list)
    }

    function handleCaracteristicasChange(e, index) {

        const { name, value } = e.target
        const list = [...caracteristicas]
        list[index][name] = value;
        setCaracteristicas(list)
    }

    function handleAddImage() {
        setImage([...image, { url: "" }])
    }

    function handleRemoveImage(index) {
        const list = [...image]
        list.splice(index, 1);

    setImage(list)
   }
    
   function handleImageChange(e, index) {
    
    const {name, value} = e.target
 
    const list = [...image]
    list[index][name] = value;
    setImage(list)
   }

    function handleChange(event) {
        const { name, value, type, checked } = event.target


       
        setFormData(prevFormData => {
            return {
                ...prevFormData,
                [name]: type === "checkbox" ? checked : value
            }
        })

    }

    function handleCategoriaChange(event) {
        const idSelecionado = parseInt(event.target.value);
        const categoriaSelecionada = categoria.find(c => c.id_categorias === idSelecionado);
        setCategoriaSelecionada(categoriaSelecionada);
      
    }

    function handleCidadeChange(event) {
        const idSelecionado = parseInt(event.target.value);
        const cidadeSelecionada = cidades.find(c => c.id_cidades === idSelecionado);
        setCidadeSelecionada(cidadeSelecionada);
      
      }
      

    React.useEffect(() => {
        async function fetchData() {

            const response = await fetch(`http://3.142.238.11:8081/category/findAll/`)

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json()
            setCategoria(data)
        }
        fetchData()
    }, []);

    React.useEffect(() => {

        async function fetchData() {

            const response = await fetch(`http://3.142.238.11:8081/cities/findAll`)

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json()
           
            setProduto(data)
            setCidades(data)
        }
        fetchData()
    }, []);

    function handleSubmit(e) {
        e.preventDefault()

        if (!categoriaSelecionada) {
            alert("Por favor, selecione uma categoria.");
            return;
            }    
          
        if (!cidadeSelecionada) {
            alert("Por favor, selecione uma cidade.");
            return;
            }   

        const body = {
            nome: nomeProduto,
            descricao: descricao,
            pontuacao: pontuacao,
            facilidades: "teste",
            localMapa: localMapa,
            distancia: endereco,
            linkMapa: "teste",
            comentarios: comentarios,
            verMais: "teste",
            caracteristicasEntityList: caracteristicas,
            imagensEntityList: image,
            categoriasEntity: categoriaSelecionada,
            cidadesEntity: cidadeSelecionada,
            reservasEntity: [],
            politicasCancelamento: politicasCancelamento,
            saudeSegurança: saudeSeguranca,
            normasCasa: normasCasa
        }

        

        fetch('http://3.142.238.11:8081/product/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                setConfirm(true)
            })
            .catch(error => {
                setSecondError(true)
            });
    }

    return (
        <div>

            <div className='reserva-container-header'>
                <div>
                    <h3>Administração de Produtos</h3>
                </div>
                <Link to={`/`}><ArrowBackIosIcon className='logo-header' /></Link>
            </div>

            <div className='criar-produto'>

                <h3>Criar propriedade</h3>
                <form className='form-container' onSubmit={handleSubmit}>

                    <div className='nome-produto-container'>

                        <label htmlFor='nomeProduto'>Nome do produto:
                            <input
                                className='input nome-do-produto'
                                required
                                id="nomeProduto"
                                size="small"
                                type="text"
                                name='nomeProduto'
                                value={nomeProduto}
                                onChange={handleChange}

                            />
                        </label>

                        <label htmlFor='categoriaValue '>Categoria:
                            <select
                                id="categoriaValue"
                                className='input'
                                onChange={handleCategoriaChange}
                                name="categoriaValue"
                                value={categoriaSelecionada?.id_categorias || ""}
                            >
                                <option value="">-- selecione uma opção --</option>
                                {
                                    // categoria.map(value => {
                                    //     const {descricao, id_categorias} = value
                                    //     return  <option key = {value.id_categorias} value={value.id_categorias}>{value.id_categorias} - {value.descricao} </option>
                                    categoria.map(c => (
                                        <option key={c.id_categorias} value={c.id_categorias}>{c.descricao}</option>
                                    ))
                                }
                            </select>
                        </label>


                        <label htmlFor='endereco'>Distância do centro:
                            <input
                                className='input descrição'
                                required
                                id="endereco"
                                name='endereco'
                                type="text"
                                value={endereco}
                                onChange={handleChange}
                            />
                        </label>

                        <label htmlFor='cidade'>Cidade:
                            <select
                                id="cidade"
                                className='input'
                                onChange={handleCidadeChange}
                                name="cidade"
                                value={cidadeSelecionada?.id_cidades || ""}
                            >
                                <option value="">-- selecione uma opção --</option>
                                {cidades.map(c => (
                                    <option key={c.id_cidades} value={c.id_cidades}>{c.nome}, {c.sigla} - {c.pais}</option>
                                ))}
                            </select>
                        </label>

                        <label htmlFor='pontuacao'>Pontuação:
                            <input
                                id="pontuacao"
                                className='input'
                                value={pontuacao}
                                onChange={handleChange}
                                name="pontuacao"
                                type='number'
                                min='0'
                                max='10'                            
                            />


                        </label>

                        <label htmlFor='localMapa'>Link mapa:
                            <input
                                id="localMapa"
                                className='input'
                                value={localMapa}
                                onChange={handleChange}
                                name="localMapa"
                                type='text'
                            />


                        </label>

                        <label htmlFor='comentarios'>Comentários:
                            <input
                                id="comentarios"
                                className='input '
                                value={comentarios}
                                onChange={handleChange}
                                name="comentarios"
                                type='text'
                            />


                        </label>


                    </div>

                    <div>
                        <label htmlFor='descricao'>Descrição: </label>
                    </div>
                    <textarea
                        className=' direcao text'
                        required
                        id="descricao"
                        name='descricao'
                        rows="5"
                        value={descricao}
                        onChange={handleChange}

                    />
                    <div className='adicionar-atributos-container'>
                        <h3>Adicionar atributos</h3>

                        <div className='adicionar-atributos'>
                            <label htmlFor='nome'>Características: 🛀, 🏊🏼‍♀️,
                                {
                                    caracteristicas.map((item, index) => {
                                        return (
                                            <div key={index} className='image-container'>
                                                <div className="first-division">
                                                    <input
                                                        className='input image'
                                                        required
                                                        id="nome"
                                                        name='nome'
                                                        type="text"
                                                        value={item.nome}
                                                        onChange={(e) => handleCaracteristicasChange(e, index)}
                                                    />

                                                    {
                                                        caracteristicas.length - 1 === index && caracteristicas.length < 5 && (

                                                            <button onClick={handleAddCaracteristicas}
                                                                className='image-btn'><AddCircleIcon /></button>

                                                        )
                                                    }
                                                </div>
                                                <div className="second-division">
                                                    {
                                                        caracteristicas.length > 1 &&
                                                        <div>
                                                            <button
                                                                className='image-btn' onClick={() => handleRemoveCaracteristicas(index)}><RemoveCircleIcon /></button>
                                                        </div>
                                                    }
                                                </div>
                                            </div>
                                        )
                                    })
                                }
                            </label>

                        </div>
                    </div>



                    <label htmlFor='imagens'>Imagens:
                        {
                            image.map((item, index) => {

                                return (
                                    <div key={index} className='image-container'>
                                        <div className="first-division">
                                            <input
                                                className='input image'
                                                required
                                                id="imagens"
                                                name='url'
                                                type="text"
                                                value={item.url}
                                                onChange={(e) => handleImageChange(e, index)}
                                            />

                                            {
                                                image.length - 1 === index && image.length < 5 && (

                                                    <button onClick={handleAddImage}
                                                        className='image-btn'><AddCircleIcon /></button>

                                                )
                                            }
                                        </div>
                                        <div className="second-division">
                                            {
                                                image.length > 1 &&
                                                <div>
                                                    <button
                                                        className='image-btn' onClick={() => handleRemoveImage(index)}><RemoveCircleIcon /></button>
                                                </div>
                                            }
                                        </div>
                                    </div>
                                )
                            })
                        }
                    </label>




                    <div>
                        <h4>Políticas do produto:</h4>

                        <div>
                            <label htmlFor='normasCasa'>Normas de casa: </label>
                        </div>
                        <textarea
                            className=' direcao'
                            required
                            id="normasCasa"
                            name='normasCasa'
                            rows="5"
                            value={normasCasa}
                            onChange={handleChange}
                        />

                        <div>
                            <label htmlFor='saudeSeguranca'>Saúde e segurança: </label>
                        </div>
                        <textarea
                            className=' direcao'
                            required
                            id="saudeSeguranca"
                            name='saudeSeguranca'
                            rows="5"
                            value={saudeSeguranca}
                            onChange={handleChange}
                        />

                        <div>
                            <label htmlFor='politicasCancelamento'>Políticas de cancelamento: </label>
                        </div>
                        <textarea
                            className=' direcao'
                            required
                            id="politicasCancelamento"
                            name='politicasCancelamento'
                            rows="5"
                            value={politicasCancelamento}
                            onChange={handleChange}
                        />


                    </div>

                    {secondError && <Alert sx={{ marginTop: '10px', '&.MuiAlert-root': { color: "rgb(249, 8, 4) !important" } }} severity="error">Não foi possível cadastrar o produto. Tente novamente mais tarde.</Alert>}

                    <button className='adm-btn' type='submit'>Criar</button>

                </form>
            </div>

            {confirm && <ReservaSucesso message={'Produto cadastrado!'} link="/" />}

        </div>
    )
}
