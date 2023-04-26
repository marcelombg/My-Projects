
import "./style.css"

export function CardProdutoReserva(props){
    function ajustarData(data){
        
        const dia = data.slice(8,10)
        const mes = data.slice(5,7)
        const ano = data.slice(0,4)

        return dia+"-"+mes+"-"+ano
        
    }

    
    return(
        <div className="card-produto-container">
            <div className="card-produto-reserva">
                <div style = {{width: '100%'}}>
                    <img className="card-produto-reserva-img" src={props.dados.img} />
                </div>
                <div className="card-produto-reserva-content" style = {{width: '100%'}}>
                    <h3 className="card-produto-title">{props.dados.nome}</h3>
                    <div className="hours">
                        <div style = {{background: '#0F5EA2', padding: '5px', borderRadius :'5px', color: '#EEF1F2'}}>
                    <p className="card-produto-chegada">Data de chegada: {ajustarData(props.dados.dataInicial)}</p>
                    <p className="card-produto-saida">Data de saída: {ajustarData(props.dados.dataFinal)}</p>
                    <p className="card-produto-entrada">Hora de entrada: {props.dados.horaEntrada}</p>
                    </div>
                </div>
                </div>
            </div>
        </div>
    )
}