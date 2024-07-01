'use client'
import {Inter as FontSans} from 'next/font/google'
import './globals.css'

import {authorise} from '@/utils/authorisationLogic'
import {getTokens} from '@/utils/token'
import {useEffect} from "react";

const fontSans = FontSans({
    subsets: ['latin'],
    variable: '--font-sans',
})

export default function RootLayout() {

    useEffect(() => {
        const tokens = getTokens()

        if (
            tokens === undefined ||
            tokens === null ||
            Object.keys(tokens).length === 0
        )
            authorise();

    }, [])

    return <h1>Сайт логистической компании</h1>
}
