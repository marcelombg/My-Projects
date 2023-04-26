import './styles.css'
import { LocationOnOutlinedIcon } from './icon'

export function SuggestBox({produto}){
    return(
        <div className="suggestBox">
            <LocationOnOutlinedIcon sx={{color:'#545776'}}/>
            <div className="suggestBox-content">
                <p className="suggestBox-content-city">{produto.nome},<span> {produto.sigla}</span></p>
                <p className="suggestBox-content-country">{produto.pais}</p>
            </div>
        </div>
    )


}