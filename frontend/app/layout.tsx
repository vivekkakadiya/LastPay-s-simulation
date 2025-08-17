import './globals.css'
import { Inter } from 'next/font/google'

const inter = Inter({ subsets: ['latin'] })

export const metadata = {
  title: 'LastPay - Car Financing Made Easy',
  description: 'Get instant car financing with integrated dealer, insurance, and bank services',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <div className="min-h-screen bg-gray-50">
          <nav className="bg-blue-600 text-white p-4">
            <div className="container mx-auto">
              <h1 className="text-2xl font-bold">LastPay</h1>
              <p className="text-blue-100">Car Financing Made Easy</p>
            </div>
          </nav>
          <main className="container mx-auto py-8 px-4">
            {children}
          </main>
        </div>
      </body>
    </html>
  )
}
