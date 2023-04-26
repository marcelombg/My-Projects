import { useState } from "react"
import { createContext, useContext } from "react"

// Ciração do Contexto
const TokenContext = createContext()

// Criação do Provedor para o Contexto
export function TokenProvider(props) {

    // State que irá controlar qual Tema a aplicação está usando
    const [token, setToken] = useState(null)
    // Função responsável por Trocar o Tema
    function changeToken(categoria) {
        setToken(categoria)
    }



    return (

        // Construção dos Elementos para utilizarmos o Contexto em nossa Aplicação, tudo o que for contido no "value" será exportado e poderá ser utilizado em Componentes que utilizarem o Hook Customizado "useTheme"
        <TokenContext.Provider value={{ token, changeToken }}>
            {props.children}
        </TokenContext.Provider>

    )

}

// Hook Personalizado que irá ser utilizado quando quisermos utilizar alguma das Funcionalidades contidas em nosso Contexto
export function useToken() {

    const context = useContext(TokenContext)

    return context

}