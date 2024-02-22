SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[description] [nvarchar](511) NOT NULL,
	[created] [datetime2](7) NOT NULL,
	[created_by] [nvarchar](255) NOT NULL
	
 CONSTRAINT [category_pk] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [category] ADD CONSTRAINT [category_name_uq] UNIQUE NONCLUSTERED ([name] ASC);

INSERT INTO [category] ([name], [description], [created], [created_by]) 
VALUES
('Camisetas', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit Camisetas', SYSUTCDATETIME(), 'Init'),
('Zapatos', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit Zapatos', SYSUTCDATETIME(), 'Init'),
('Jeans', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit Jeans', SYSUTCDATETIME(), 'Init'),
('Pantalones', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit Pantalones', SYSUTCDATETIME(), 'Init'),
('Vestidos', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit Vestidos', SYSUTCDATETIME(), 'Init');