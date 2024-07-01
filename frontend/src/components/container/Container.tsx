import {Card} from '@/components/ui/card'
import dynamic from "next/dynamic";


const Sidebar = dynamic(
    () => import('@/components/side-bar/side-bar'),
    {ssr: false}
)
export const Container = ({
                              children,
                          }: Readonly<{
    children: React.ReactNode
}>) => {
    return (
        <div className='flex w-full p-4'>
            <Sidebar/>

            <div className='w-full flex justify-center'>
                <Card className='w-3/4 p-4'>{children}</Card>
            </div>
        </div>
    )
}
