import { useState } from "react"
import { createContext, useContext } from "react"

// Ciração do Contexto
const FilterContext = createContext()

// Criação do Provedor para o Contexto
export function FilterProvider(props) {

    // State que irá controlar qual Tema a aplicação está usando
    const [filter, setFilter] = useState("")
    // Função responsável por Trocar o Tema
    function changeFilter(categoria) {
        setFilter(categoria)
    }



    return (

        // Construção dos Elementos para utilizarmos o Contexto em nossa Aplicação, tudo o que for contido no "value" será exportado e poderá ser utilizado em Componentes que utilizarem o Hook Customizado "useTheme"
        <FilterContext.Provider value={{ filter, changeFilter }}>
            {props.children}
        </FilterContext.Provider>

    )

}

// Hook Personalizado que irá ser utilizado quando quisermos utilizar alguma das Funcionalidades contidas em nosso Contexto
export function useFilterCategoria() {

    const context = useContext(FilterContext)

    return context

}
